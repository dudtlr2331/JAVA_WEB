package pro17.sec01.sec03.brd02;

articleVO.setContent(content);
articleVO.setImageFileName(imageFileName);
boardService.addArticle(articleVO);
nextPage = "/board/listArticles.do";
}else {
nextPage = "/board02/listArticles.jsp";
}

RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
dispatch.forward(request, response);
} catch (Exception e) {
e.printStackTrace();
}
}

private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Map<String, String> articleMap = new HashMap<String, String>();
String encoding = "utf-8";
File currentDirPath = new File(ARTICLE_IMAGE_REPO);
DiskFileItemFactory factory = new DiskFileItemFactory();
factory.setRepository(currentDirPath);
factory.setSizeThreshold(1024 * 1024);
ServletFileUpload upload = new ServletFileUpload(factory);
try {
List items = upload.parseRequest(request);
for (int i = 0; i < items.size(); i++) {
FileItem fileItem = (FileItem) items.get(i);
if (fileItem.isFormField()) {
	System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
	articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
} else {
	System.out.println("파라미터명:" + fileItem.getFieldName());
	//System.out.println("파일명:" + fileItem.getName());
	System.out.println("파일크기:" + fileItem.getSize() + "bytes");
	//articleMap.put(fileItem.getFieldName(), fileItem.getName());
	if (fileItem.getSize() > 0) {
		int idx = fileItem.getName().lastIndexOf("\\");
		if (idx == -1) {
			idx = fileItem.getName().lastIndexOf("/");
		}

		String fileName = fileItem.getName().substring(idx + 1);
		System.out.println("파일명:" + fileName);
		articleMap.put(fileItem.getFieldName(), fileName);  //익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장
		File uploadFile = new File(currentDirPath + "\\" + fileName);
		fileItem.write(uploadFile);

	} // end if
} // end if
} // end for
} catch (Exception e) {
e.printStackTrace();
}
return articleMap;
}

}
